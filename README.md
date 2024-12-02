# Matrix Multiplication Optimization

This repo explores various optimization techniques for matrix multiplication, aiming to improve computational efficiency. Key techniques include:

- **Strassen's Algorithm**: faster algorithm for faster matrix multiplication.
- **Loop Unrolling**: Reduces loop overhead to speed up computation.
- **Cache Optimization**: Uses cache-friendly methods like tiling to minimize memory latency.
- use of **Sparse Matrices**: Optimizes multiplication by skipping zero elements to improve efficiency.
- **Paralelization**: multiplication executed simultaneously across multiple threads

## Getting Started
1. Clone the repository:
    ```bash
    git clone https://github.com/sajdoann/matrix-multiplication.git
    ```
2. Run the provided scripts to benchmark matrix multiplication with different optimizations.

## Parameters

The benchmarking class `MatrixBenchmark` allows for the configuration of various parameters to evaluate different matrix multiplication techniques. Below is an overview of the configurable parameters and their possible values.

- **Matrix Size (`n`)**: Defines the size of the square matrices to be multiplied.

- **Algorithm (`algorithm`)**: Specifies the matrix multiplication algorithm to be used.
  - Possible values: `classic`, `strassen`, `sparse`, `cache`, `loop`, `cachesparse`, `paralel`

- **Sparsity (`sparsity`)**: Controls the sparsity of the matrices, with 0 representing a dense matrix and higher values indicating more zeros.
  - Possible values: `0` (dense), `0.5` (half zeros), `0.9` (mostly zeros)

- **Block Size (`blockSize`)**: Defines the block size used in certain algorithms such as cache optimization and cache-aware sparse matrix multiplication.

- **Number of Threads (`nThreads`)**: Defines the number of threads used in parallelized algorithms.

## Setup

- **denseA** and **denseB** are randomly populated dense matrices.
- **sparseA** and **sparseB** are sparse matrices where elements are added only if they are non-zero.

### Matrix Initialization:

- For dense matrices, values are randomly generated and based on the specified sparsity.
- Sparse matrices are populated only with non-zero values.


